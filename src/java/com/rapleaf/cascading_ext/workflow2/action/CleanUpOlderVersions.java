package com.rapleaf.cascading_ext.workflow2.action;

import java.util.Arrays;

import com.liveramp.cascading_ext.megadesk.StoreReaderLocker;
import com.rapleaf.cascading_ext.datastore.VersionedBucketDataStore;
import com.rapleaf.cascading_ext.workflow2.Action;
import com.rapleaf.formats.datastore.VersionDeletionDeterminer;
import com.rapleaf.formats.datastore.VersionedStore;

public class CleanUpOlderVersions extends Action {
  private final int numVersionsToKeep;
  private final Iterable<? extends VersionedBucketDataStore> versionedDataStores;

  public CleanUpOlderVersions(String checkpointToken, String tmpRoot, int numVersionsToKeep, VersionedBucketDataStore store) {
    this(checkpointToken, tmpRoot, numVersionsToKeep, Arrays.asList(store));
  }

  public CleanUpOlderVersions(String checkpointToken, String tmpRoot, int numVersionsToKeep, Iterable<? extends VersionedBucketDataStore> versionedDataStores) {
    super(checkpointToken, tmpRoot);

    this.numVersionsToKeep = numVersionsToKeep;
    this.versionedDataStores = versionedDataStores;
  }

  @Override
  protected void execute() throws Exception {
    for (VersionedBucketDataStore versionedDataStore : versionedDataStores) {
      versionedDataStore.getVersionedStore().deleteOlderVersions(
          numVersionsToKeep, new StoreLockDeletionDeterminer(this.getLockProvider()), true);
    }
  }

  private static class StoreLockDeletionDeterminer implements VersionDeletionDeterminer {

    private final StoreReaderLocker provider;

    public StoreLockDeletionDeterminer(StoreReaderLocker provider) {
      this.provider = provider;
    }

    public boolean canDelete(VersionedStore store, long version) {
      return !provider.hasReaders(store, version);
    }
  }
}
