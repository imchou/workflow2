
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.liveramp.databases.workflow_db.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.rapleaf.jack.AssociationType;
import com.rapleaf.jack.AttributesWithId;
import com.rapleaf.jack.BelongsToAssociation;
import com.rapleaf.jack.DefaultAssociationMetadata;
import com.rapleaf.jack.HasManyAssociation;
import com.rapleaf.jack.HasOneAssociation;
import com.rapleaf.jack.IAssociationMetadata;
import com.rapleaf.jack.IModelAssociationMetadata;
import com.rapleaf.jack.ModelIdWrapper;
import com.rapleaf.jack.ModelWithId;
import com.rapleaf.jack.queries.AbstractTable;
import com.rapleaf.jack.queries.Column;

import com.liveramp.databases.workflow_db.IDatabases;
import com.rapleaf.jack.util.JackUtility;

public class WorkflowAttemptDatastore extends ModelWithId<WorkflowAttemptDatastore, IDatabases> implements Comparable<WorkflowAttemptDatastore>{
  
  public static final long serialVersionUID = 7305390057495532705L;

  public static class Tbl extends AbstractTable<WorkflowAttemptDatastore.Attributes, WorkflowAttemptDatastore> {
    public final Column<Long> ID;
    public final Column<Integer> WORKFLOW_ATTEMPT_ID;
    public final Column<String> NAME;
    public final Column<String> PATH;
    public final Column<String> CLASS_NAME;

    private Tbl(String alias) {
      super("workflow_attempt_datastores", alias, WorkflowAttemptDatastore.Attributes.class, WorkflowAttemptDatastore.class);
      this.ID = Column.fromId(alias);
      this.WORKFLOW_ATTEMPT_ID = Column.fromField(alias, _Fields.workflow_attempt_id, Integer.class);
      this.NAME = Column.fromField(alias, _Fields.name, String.class);
      this.PATH = Column.fromField(alias, _Fields.path, String.class);
      this.CLASS_NAME = Column.fromField(alias, _Fields.class_name, String.class);
      Collections.addAll(this.allColumns, ID, WORKFLOW_ATTEMPT_ID, NAME, PATH, CLASS_NAME);
    }

    public static Tbl as(String alias) {
      return new Tbl(alias);
    }
  }

  public static final Tbl TBL = new Tbl("workflow_attempt_datastores");
  public static final Column<Long> ID = TBL.ID;
  public static final Column<Integer> WORKFLOW_ATTEMPT_ID = TBL.WORKFLOW_ATTEMPT_ID;
  public static final Column<String> NAME = TBL.NAME;
  public static final Column<String> PATH = TBL.PATH;
  public static final Column<String> CLASS_NAME = TBL.CLASS_NAME;

  private final Attributes attributes;

  private transient WorkflowAttemptDatastore.Id cachedTypedId;

  // Associations
  private BelongsToAssociation<WorkflowAttempt> __assoc_workflow_attempt;
  private HasManyAssociation<StepAttemptDatastore> __assoc_step_attempt_datastores;

  public enum _Fields {
    workflow_attempt_id,
    name,
    path,
    class_name,
  }

  @Override
  public WorkflowAttemptDatastore.Id getTypedId() {
    if (cachedTypedId == null) {
      cachedTypedId = new WorkflowAttemptDatastore.Id(this.getId());
    }
    return cachedTypedId;
  }

  public WorkflowAttemptDatastore(long id, final int workflow_attempt_id, final String name, final String path, final String class_name, IDatabases databases) {
    super(databases);
    attributes = new Attributes(id, workflow_attempt_id, name, path, class_name);
    this.__assoc_workflow_attempt = new BelongsToAssociation<>(databases.getWorkflowDb().workflowAttempts(), (long) getWorkflowAttemptId());
    this.__assoc_step_attempt_datastores = new HasManyAssociation<>(databases.getWorkflowDb().stepAttemptDatastores(), "workflow_attempt_datastore_id", getId());
  }

  public WorkflowAttemptDatastore(long id, final int workflow_attempt_id, final String name, final String path, final String class_name) {
    super(null);
    attributes = new Attributes(id, workflow_attempt_id, name, path, class_name);
  }

  public static WorkflowAttemptDatastore newDefaultInstance(long id) {
    return new WorkflowAttemptDatastore(id, 0, "", "", "");
  }

  public WorkflowAttemptDatastore(Attributes attributes, IDatabases databases) {
    super(databases);
    this.attributes = attributes;

    if (databases != null) {
      this.__assoc_workflow_attempt = new BelongsToAssociation<>(databases.getWorkflowDb().workflowAttempts(), (long) getWorkflowAttemptId());
      this.__assoc_step_attempt_datastores = new HasManyAssociation<>(databases.getWorkflowDb().stepAttemptDatastores(), "workflow_attempt_datastore_id", getId());
    }
  }

  public WorkflowAttemptDatastore(Attributes attributes) {
    this(attributes, (IDatabases) null);
  }

  public WorkflowAttemptDatastore(long id, Map<Enum, Object> fieldsMap) {
    super(null);
    attributes = new Attributes(id, fieldsMap);
  }

  public WorkflowAttemptDatastore (WorkflowAttemptDatastore other) {
    this(other, (IDatabases)null);
  }

  public WorkflowAttemptDatastore (WorkflowAttemptDatastore other, IDatabases databases) {
    super(databases);
    attributes = new Attributes(other.getAttributes());

    if (databases != null) {
      this.__assoc_workflow_attempt = new BelongsToAssociation<>(databases.getWorkflowDb().workflowAttempts(), (long) getWorkflowAttemptId());
      this.__assoc_step_attempt_datastores = new HasManyAssociation<>(databases.getWorkflowDb().stepAttemptDatastores(), "workflow_attempt_datastore_id", getId());
    }
  }

  public Attributes getAttributes() {
    return attributes;
  }

  public int getWorkflowAttemptId() {
    return attributes.getWorkflowAttemptId();
  }

  public WorkflowAttemptDatastore setWorkflowAttemptId(int newval) {
    attributes.setWorkflowAttemptId(newval);
    if(__assoc_workflow_attempt != null){
      this.__assoc_workflow_attempt.setOwnerId(newval);
    }
    cachedHashCode = 0;
    return this;
  }

  public String getName() {
    return attributes.getName();
  }

  public WorkflowAttemptDatastore setName(String newval) {
    attributes.setName(newval);
    cachedHashCode = 0;
    return this;
  }

  public String getPath() {
    return attributes.getPath();
  }

  public WorkflowAttemptDatastore setPath(String newval) {
    attributes.setPath(newval);
    cachedHashCode = 0;
    return this;
  }

  public String getClassName() {
    return attributes.getClassName();
  }

  public WorkflowAttemptDatastore setClassName(String newval) {
    attributes.setClassName(newval);
    cachedHashCode = 0;
    return this;
  }

  public void setField(_Fields field, Object value) {
    switch (field) {
      case workflow_attempt_id:
        setWorkflowAttemptId((Integer)value);
        break;
      case name:
        setName((String)value);
        break;
      case path:
        setPath((String)value);
        break;
      case class_name:
        setClassName((String)value);
        break;
      default:
        throw new IllegalStateException("Invalid field: " + field);
    }
  }
  
  public void setField(String fieldName, Object value) {
    if (fieldName.equals("workflow_attempt_id")) {
      setWorkflowAttemptId((Integer)  value);
      return;
    }
    if (fieldName.equals("name")) {
      setName((String)  value);
      return;
    }
    if (fieldName.equals("path")) {
      setPath((String)  value);
      return;
    }
    if (fieldName.equals("class_name")) {
      setClassName((String)  value);
      return;
    }
    throw new IllegalStateException("Invalid field: " + fieldName);
  }

  public static Class getFieldType(_Fields field) {
    switch (field) {
      case workflow_attempt_id:
        return int.class;
      case name:
        return String.class;
      case path:
        return String.class;
      case class_name:
        return String.class;
      default:
        throw new IllegalStateException("Invalid field: " + field);
    }    
  }

  public static Class getFieldType(String fieldName) {    
    if (fieldName.equals("workflow_attempt_id")) {
      return int.class;
    }
    if (fieldName.equals("name")) {
      return String.class;
    }
    if (fieldName.equals("path")) {
      return String.class;
    }
    if (fieldName.equals("class_name")) {
      return String.class;
    }
    throw new IllegalStateException("Invalid field name: " + fieldName);
  }

  public WorkflowAttempt getWorkflowAttempt() throws IOException {
    return __assoc_workflow_attempt.get();
  }

  public List<StepAttemptDatastore> getStepAttemptDatastores() throws IOException {
    return __assoc_step_attempt_datastores.get();
  }

  @Override
  public Object getField(String fieldName) {
    if (fieldName.equals("id")) {
      return getId();
    }
    if (fieldName.equals("workflow_attempt_id")) {
      return getWorkflowAttemptId();
    }
    if (fieldName.equals("name")) {
      return getName();
    }
    if (fieldName.equals("path")) {
      return getPath();
    }
    if (fieldName.equals("class_name")) {
      return getClassName();
    }
    throw new IllegalStateException("Invalid field name: " + fieldName);
  }

  public Object getField(_Fields field) {
    switch (field) {
      case workflow_attempt_id:
        return getWorkflowAttemptId();
      case name:
        return getName();
      case path:
        return getPath();
      case class_name:
        return getClassName();
    }
    throw new IllegalStateException("Invalid field: " + field);
  }

  public boolean hasField(String fieldName) {
    if (fieldName.equals("id")) {
      return true;
    }
    if (fieldName.equals("workflow_attempt_id")) {
      return true;
    }
    if (fieldName.equals("name")) {
      return true;
    }
    if (fieldName.equals("path")) {
      return true;
    }
    if (fieldName.equals("class_name")) {
      return true;
    }
    return false;
  }

  public static Object getDefaultValue(_Fields field) {
    switch (field) {
      case workflow_attempt_id:
        return null;
      case name:
        return null;
      case path:
        return null;
      case class_name:
        return null;
    }
    throw new IllegalStateException("Invalid field: " + field);
  }

  @Override
  public Set<Enum> getFieldSet() {
    Set set = EnumSet.allOf(_Fields.class);
    return set;
  }

  @Override
  public WorkflowAttemptDatastore getCopy() {
    return getCopy(databases);
  }

  @Override
  public WorkflowAttemptDatastore getCopy(IDatabases databases) {
    return new WorkflowAttemptDatastore(this, databases);
  }

  @Override
  public boolean save() throws IOException {
    return databases.getWorkflowDb().workflowAttemptDatastores().save(this);
  }

  public WorkflowAttempt createWorkflowAttempt(final int workflow_execution_id, final String system_user, final String priority, final String pool, final String host) throws IOException {
 
    WorkflowAttempt newWorkflowAttempt = databases.getWorkflowDb().workflowAttempts().create(workflow_execution_id, system_user, priority, pool, host);
    setWorkflowAttemptId(JackUtility.safeLongToInt(newWorkflowAttempt.getId()));
    save();
    __assoc_workflow_attempt.clearCache();
    return newWorkflowAttempt;
  }

  public WorkflowAttempt createWorkflowAttempt(final int workflow_execution_id, final String system_user, final String shutdown_reason, final String priority, final String pool, final String host, final Long start_time, final Long end_time, final Integer status, final Long last_heartbeat, final String launch_dir, final String launch_jar, final String error_email, final String info_email, final String scm_remote, final String commit_revision, final String description, final Long last_heartbeat_epoch) throws IOException {
 
    WorkflowAttempt newWorkflowAttempt = databases.getWorkflowDb().workflowAttempts().create(workflow_execution_id, system_user, shutdown_reason, priority, pool, host, start_time, end_time, status, last_heartbeat, launch_dir, launch_jar, error_email, info_email, scm_remote, commit_revision, description, last_heartbeat_epoch);
    setWorkflowAttemptId(JackUtility.safeLongToInt(newWorkflowAttempt.getId()));
    save();
    __assoc_workflow_attempt.clearCache();
    return newWorkflowAttempt;
  }

  public WorkflowAttempt createWorkflowAttempt() throws IOException {
 
    WorkflowAttempt newWorkflowAttempt = databases.getWorkflowDb().workflowAttempts().create(0, "", "", "", "");
    setWorkflowAttemptId(JackUtility.safeLongToInt(newWorkflowAttempt.getId()));
    save();
    __assoc_workflow_attempt.clearCache();
    return newWorkflowAttempt;
  }

  public String toString() {
    return "<WorkflowAttemptDatastore"
        + " id: " + this.getId()
        + " workflow_attempt_id: " + getWorkflowAttemptId()
        + " name: " + getName()
        + " path: " + getPath()
        + " class_name: " + getClassName()
        + ">";
  }

  public void unsetAssociations() {
    unsetDatabaseReference();
    __assoc_workflow_attempt = null;
    __assoc_step_attempt_datastores = null;
  }

  public int compareTo(WorkflowAttemptDatastore that) {
    return Long.valueOf(this.getId()).compareTo(that.getId());
  }
  
  
  public static class Attributes extends AttributesWithId {
    
    public static final long serialVersionUID = 9140369847499437817L;

    // Fields
    private int __workflow_attempt_id;
    private String __name;
    private String __path;
    private String __class_name;

    public Attributes(long id) {
      super(id);
    }

    public Attributes(long id, final int workflow_attempt_id, final String name, final String path, final String class_name) {
      super(id);
      this.__workflow_attempt_id = workflow_attempt_id;
      this.__name = name;
      this.__path = path;
      this.__class_name = class_name;
    }

    public static Attributes newDefaultInstance(long id) {
      return new Attributes(id, 0, "", "", "");
    }

    public Attributes(long id, Map<Enum, Object> fieldsMap) {
      super(id);
      int workflow_attempt_id = (Integer)fieldsMap.get(WorkflowAttemptDatastore._Fields.workflow_attempt_id);
      String name = (String)fieldsMap.get(WorkflowAttemptDatastore._Fields.name);
      String path = (String)fieldsMap.get(WorkflowAttemptDatastore._Fields.path);
      String class_name = (String)fieldsMap.get(WorkflowAttemptDatastore._Fields.class_name);
      this.__workflow_attempt_id = workflow_attempt_id;
      this.__name = name;
      this.__path = path;
      this.__class_name = class_name;
    }

    public Attributes(Attributes other) {
      super(other.getId());
      this.__workflow_attempt_id = other.getWorkflowAttemptId();
      this.__name = other.getName();
      this.__path = other.getPath();
      this.__class_name = other.getClassName();
    }

    public int getWorkflowAttemptId() {
      return __workflow_attempt_id;
    }

    public Attributes setWorkflowAttemptId(int newval) {
      this.__workflow_attempt_id = newval;
      cachedHashCode = 0;
      return this;
    }

    public String getName() {
      return __name;
    }

    public Attributes setName(String newval) {
      this.__name = newval;
      cachedHashCode = 0;
      return this;
    }

    public String getPath() {
      return __path;
    }

    public Attributes setPath(String newval) {
      this.__path = newval;
      cachedHashCode = 0;
      return this;
    }

    public String getClassName() {
      return __class_name;
    }

    public Attributes setClassName(String newval) {
      this.__class_name = newval;
      cachedHashCode = 0;
      return this;
    }

    public void setField(_Fields field, Object value) {
      switch (field) {
        case workflow_attempt_id:
          setWorkflowAttemptId((Integer)value);
          break;
        case name:
          setName((String)value);
          break;
        case path:
          setPath((String)value);
          break;
        case class_name:
          setClassName((String)value);
          break;
        default:
          throw new IllegalStateException("Invalid field: " + field);
      }
    }

    public void setField(String fieldName, Object value) {
      if (fieldName.equals("workflow_attempt_id")) {
        setWorkflowAttemptId((Integer)value);
        return;
      }
      if (fieldName.equals("name")) {
        setName((String)value);
        return;
      }
      if (fieldName.equals("path")) {
        setPath((String)value);
        return;
      }
      if (fieldName.equals("class_name")) {
        setClassName((String)value);
        return;
      }
      throw new IllegalStateException("Invalid field: " + fieldName);
    }

    public static Class getFieldType(_Fields field) {
      switch (field) {
        case workflow_attempt_id:
          return int.class;
        case name:
          return String.class;
        case path:
          return String.class;
        case class_name:
          return String.class;
        default:
          throw new IllegalStateException("Invalid field: " + field);
      }    
    }

    public static Class getFieldType(String fieldName) {    
      if (fieldName.equals("workflow_attempt_id")) {
        return int.class;
      }
      if (fieldName.equals("name")) {
        return String.class;
      }
      if (fieldName.equals("path")) {
        return String.class;
      }
      if (fieldName.equals("class_name")) {
        return String.class;
      }
      throw new IllegalStateException("Invalid field name: " + fieldName);
    }

    @Override
    public Object getField(String fieldName) {
      if (fieldName.equals("id")) {
        return getId();
      }
      if (fieldName.equals("workflow_attempt_id")) {
        return getWorkflowAttemptId();
      }
      if (fieldName.equals("name")) {
        return getName();
      }
      if (fieldName.equals("path")) {
        return getPath();
      }
      if (fieldName.equals("class_name")) {
        return getClassName();
      }
      throw new IllegalStateException("Invalid field name: " + fieldName);
    }

    public Object getField(_Fields field) {
      switch (field) {
        case workflow_attempt_id:
          return getWorkflowAttemptId();
        case name:
          return getName();
        case path:
          return getPath();
        case class_name:
          return getClassName();
      }
      throw new IllegalStateException("Invalid field: " + field);
    }

    public boolean hasField(String fieldName) {
      if (fieldName.equals("id")) {
        return true;
      }
      if (fieldName.equals("workflow_attempt_id")) {
        return true;
      }
      if (fieldName.equals("name")) {
        return true;
      }
      if (fieldName.equals("path")) {
        return true;
      }
      if (fieldName.equals("class_name")) {
        return true;
      }
      return false;
    }

    public static Object getDefaultValue(_Fields field) {
      switch (field) {
        case workflow_attempt_id:
          return null;
        case name:
          return null;
        case path:
          return null;
        case class_name:
          return null;
      }
      throw new IllegalStateException("Invalid field: " + field);
    }
    
    @Override
    public Set<Enum> getFieldSet() {
      Set set = EnumSet.allOf(_Fields.class);
      return set;
    }
    
    public String toString() {
      return "<WorkflowAttemptDatastore.Attributes"
          + " workflow_attempt_id: " + getWorkflowAttemptId()
          + " name: " + getName()
          + " path: " + getPath()
          + " class_name: " + getClassName()
          + ">";
    }
  }

  public static class Id implements ModelIdWrapper<WorkflowAttemptDatastore.Id> {
    public static final long serialVersionUID = 1L;

    private final long id;

    public Id(Long id) {
      this.id = id;
    }

    @Override
    public Long getId() {
      return id;
    }

    @Override
    public int compareTo(Id other) {
      return this.getId().compareTo(other.getId());
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Id) {
        return this.getId().equals(((Id)other).getId());
      }
      return false;
    }

    @Override
    public int hashCode() {
      return this.getId().hashCode();
    }

    @Override
    public String toString() {
      return "<WorkflowAttemptDatastore.Id: " + this.getId() + ">";
    }
  }

  public static Set<Attributes> convertToAttributesSet(Collection<WorkflowAttemptDatastore> models) {
    return models.stream()
        .map(WorkflowAttemptDatastore::getAttributes)
        .collect(Collectors.toSet());
  }

  public static class AssociationMetadata implements IModelAssociationMetadata {

    private List<IAssociationMetadata> meta = new ArrayList<>();

    public AssociationMetadata(){
      meta.add(new DefaultAssociationMetadata(AssociationType.BELONGS_TO, WorkflowAttemptDatastore.class, WorkflowAttempt.class, "workflow_attempt_id"));
      meta.add(new DefaultAssociationMetadata(AssociationType.HAS_MANY, WorkflowAttemptDatastore.class, StepAttemptDatastore.class, "workflow_attempt_datastore_id"));
    }

    @Override
    public List<IAssociationMetadata> getAssociationMetadata() {
      return meta;
    }
  }

}
