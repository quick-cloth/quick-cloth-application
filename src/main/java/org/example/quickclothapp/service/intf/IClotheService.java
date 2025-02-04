package org.example.quickclothapp.service.intf;

import org.example.quickclothapp.exception.DataServiceException;
import org.example.quickclothapp.model.*;
import org.example.quickclothapp.payload.request.DonationRequest;

import java.util.List;
import java.util.UUID;

public interface IClotheService {
    Clothe findClotheByUuid(UUID uuid) throws DataServiceException;
    TypeClothe findTypeClotheByUuid(UUID typeClotheUuid) throws DataServiceException;
    TypeGender findTypeGenderByUuid(UUID typeGenderUuid) throws DataServiceException;
    TypeStage findTypeStageByUuid(UUID typeStageUuid) throws DataServiceException;
    List<TypeStage> findAllTypeStage() throws DataServiceException;
    List<TypeGender> findAllTypeGender() throws DataServiceException;
    List<TypeClothe> findAllTypeClothe() throws DataServiceException;
    Clothe findClotheByAllTypes(UUID typeClotheUuid, UUID typeGenderUuid, UUID typeStageUuid) throws DataServiceException;
    Clothe saveClothe(UUID typeClotheUuid, UUID typeGenderUuid, UUID typeStageUuid) throws DataServiceException;
}
