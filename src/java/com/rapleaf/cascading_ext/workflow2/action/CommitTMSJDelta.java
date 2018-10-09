package com.rapleaf.cascading_ext.workflow2.action;

import com.rapleaf.cascading_ext.datastore.BucketDataStore;
import com.rapleaf.cascading_ext.msj_tap.store.TMSJDataStore;
import org.apache.hadoop.io.BytesWritable;
import org.apache.thrift.TBase;

public class CommitTMSJDelta<T extends TBase<?, ?>> extends CommitMSJDelta<T, BytesWritable> {
  public CommitTMSJDelta(String checkpointToken, BucketDataStore<T> versionToPersist, TMSJDataStore<T> store) {
    super(checkpointToken, versionToPersist, store);
  }
}
