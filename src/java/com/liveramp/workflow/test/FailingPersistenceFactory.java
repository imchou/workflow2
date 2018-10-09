package com.liveramp.workflow.test;

import java.io.IOException;
import java.util.Set;

import com.google.common.collect.Sets;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.liveramp.cascading_ext.resource.ResourceDeclarerFactory;
import com.liveramp.cascading_ext.resource.ResourceManager;
import com.liveramp.commons.util.MultiShutdownHook;
import com.liveramp.importer.generated.AppType;
import com.liveramp.java_support.alerts_handler.AlertsHandler;
import com.liveramp.java_support.functional.Fn;
import com.liveramp.java_support.functional.Fns;
import com.liveramp.workflow_core.JVMState;
import com.liveramp.workflow.state.DbHadoopWorkflow;
import com.liveramp.workflow_db_state.InitializedDbPersistence;
import com.liveramp.workflow_state.WorkflowRunnerNotification;
import com.liveramp.workflow_state.WorkflowStatePersistence;
import com.rapleaf.cascading_ext.workflow2.Step;
import com.rapleaf.cascading_ext.workflow2.options.WorkflowOptions;
import com.rapleaf.cascading_ext.workflow2.state.WorkflowPersistenceFactory;

public class FailingPersistenceFactory extends WorkflowPersistenceFactory<Step, InitializedDbPersistence, WorkflowOptions, DbHadoopWorkflow> {

  protected final WorkflowPersistenceFactory<Step, InitializedDbPersistence, WorkflowOptions, DbHadoopWorkflow> delegate;
  private final Set<String> stepsToFailFullNames;

  public FailingPersistenceFactory(WorkflowPersistenceFactory delegate, StepNameBuilder stepNameBuilder) {
    this(delegate, Sets.newHashSet(stepNameBuilder));
  }

  /**
   * @param stepNameBuilders any given steps are failed after they finish executing
   */
  public FailingPersistenceFactory(WorkflowPersistenceFactory delegate, Set<StepNameBuilder> stepNameBuilders) {
    super(new JVMState());
    this.delegate = delegate;
    this.stepsToFailFullNames = Sets.newHashSet(Fns.map(new Fn<StepNameBuilder, String>() {
      @Override
      public String apply(StepNameBuilder input) {
        return input.getCompositeStepName();
      }
    }, stepNameBuilders));
  }

  @Override
  public WorkflowStatePersistence prepare(InitializedDbPersistence persistence, DirectedGraph<Step, DefaultEdge> flatSteps) {
    return new FailingPersistence(delegate.prepare(persistence, flatSteps), stepsToFailFullNames);
  }

  @Override
  public DbHadoopWorkflow construct(String workflowName, WorkflowOptions options, InitializedDbPersistence initialized, ResourceManager manager, MultiShutdownHook hook) {
    return new DbHadoopWorkflow(workflowName, options, initialized, this, manager, hook);
  }

  @Override
  public InitializedDbPersistence initializeInternal(String name, String scopeId, String description, AppType appType, String host, String username, String pool, String priority, String launchDir, String launchJar, Set<WorkflowRunnerNotification> configuredNotifications, AlertsHandler providedHandler, Class<? extends ResourceDeclarerFactory> resourceFactory, String remote, String implementationBuild) throws IOException {
    return delegate.initializeInternal(name, scopeId, description, appType, host, username, pool, priority, launchDir, launchJar, configuredNotifications, providedHandler, resourceFactory, remote, implementationBuild);
  }

  public static class IntentionallyFailedStepException extends RuntimeException {
    public IntentionallyFailedStepException(String message) {
      super(message);
    }
  }

  private static class FailingPersistence extends ForwardingPersistence {

    private final Set<String> stepsToFailFullNames;

    private FailingPersistence(WorkflowStatePersistence delegate, Set<String> stepsToFailFullNames) {
      super(delegate);
      this.stepsToFailFullNames = stepsToFailFullNames;
    }

    @Override
    public void markStepCompleted(String stepToken) throws IOException {
      delegatePersistence.markStepCompleted(stepToken);
      if (stepsToFailFullNames.contains(stepToken)) {
        IntentionallyFailedStepException exception = new IntentionallyFailedStepException(String.format("Failed step intentionally: %s", stepToken));
        delegatePersistence.markStepFailed(stepToken, exception);
        throw exception;
      }
    }

  }
}
