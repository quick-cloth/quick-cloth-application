package org.example.quickclothapp.service.intf;

import org.example.quickclothapp.exception.DataServiceException;
import org.example.quickclothapp.model.User;
import org.example.quickclothapp.payload.request.UserRequest;
import org.example.quickclothapp.payload.response.MessageResponse;

import java.util.UUID;

public interface IUserService {
    User findUserByUuid(UUID uuid) throws DataServiceException;
    User findUserByDocumentNumber(String documentNumber) throws DataServiceException;
    MessageResponse saveUserClient(UserRequest user) throws DataServiceException;
    MessageResponse saveUserFoundation(UserRequest user, UUID foundationUuid) throws DataServiceException;
    MessageResponse saveUserBank(UserRequest user, UUID clotheBankUuid) throws DataServiceException;
    MessageResponse saveUserWardrope(UserRequest user, UUID wardRopeUuid) throws DataServiceException;
}
