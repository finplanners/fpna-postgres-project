package com.msciq.storage.repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GCPStorageRepository extends DatastoreRepository {
    //List<FPDetails> findAllByUpdatedBy(String updatedBy);

}
