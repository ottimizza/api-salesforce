package br.com.ottimizza.salesforceclientapi.services;

import org.springframework.stereotype.Service;

@Service
public interface SObjectService {

    void create();

    void patch();

    void delete();

    //
    //

    void get();

    void getByExternalId();

    void getById();

}