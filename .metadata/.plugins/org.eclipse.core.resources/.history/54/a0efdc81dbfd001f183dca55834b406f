package com.ps.assignment.employeeManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.helper.UserHelper;
import com.ps.assignment.employeeManagement.model.Address;
import com.ps.assignment.employeeManagement.model.Bank;
import com.ps.assignment.employeeManagement.model.Company;
import com.ps.assignment.employeeManagement.model.Coordinates;
import com.ps.assignment.employeeManagement.model.Crypto;
import com.ps.assignment.employeeManagement.model.Hair;
import com.ps.assignment.employeeManagement.model.User;
import com.ps.assignment.employeeManagement.repository.UserRepository;
import com.ps.assignment.employeeManagement.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private ExternalApiCaller externalApiCaller;

    @Value("${employeeManagement.startupConfigs.thirdPartyApi}")
    private String API_URL;

    @Override
    @CircuitBreaker(name = "externalApiCallerCircuitBreaker", fallbackMethod = "fallbackGetDataFromExternalAPI")
    public JSONObject getDataFromExternalAPI() {
        LOG.info("In getDataFromExternalAPI()");
        JSONObject result = null;
        try {
            String response = externalApiCaller.callApi();
            result = new JSONObject(response);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public JSONObject fallbackGetDataFromExternalAPI(Throwable t) {
        LOG.error("Fallback method called due to: " + t.getMessage(), t);
        return new JSONObject().put("error", "External API call failed");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean loadUsersFromExternalAPI(JSONArray usersArray) {
        LOG.info("In loadUsersFromExternalAPI()");
        Boolean status = false;
        try {
            usersArray.forEach(jsonUser -> {
                String email = ((JSONObject) jsonUser).getString("email");
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) {
                    return;
                }
                JSONObject addressJson = (JSONObject) ((JSONObject) jsonUser).get("address");
                JSONObject coordinatesJson = (JSONObject) addressJson.get("coordinates");
                Coordinates coordinates = userHelper.findAndSaveCoordinates(coordinatesJson);
                Address address = userHelper.findAndSaveAddress(addressJson, coordinates);
                Crypto crypto = userHelper.findAndSaveCrypto((JSONObject) ((JSONObject) jsonUser).get("crypto"));
                Hair hair = userHelper.findAndSaveHair((JSONObject) ((JSONObject) jsonUser).get("hair"));
                Bank bank = userHelper.findAndSaveBank((JSONObject) ((JSONObject) jsonUser).get("bank"));
                Company company = userHelper
                        .findAndSaveCompany((JSONObject) ((JSONObject) jsonUser).get("company"));
                userHelper.createAndSaveUser((JSONObject) jsonUser, hair, address, bank, company, crypto);
            });
            status = true;
            LOG.info("Users saved successfully !!");
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return status;
    }

    @Override
    public List<UserDto> findAllUser() {
        LOG.info("In findAllUser()");
        List<UserDto> users = new ArrayList<>();
        try {
            List<Object[]> result = userRepository.fetchUsers();
            LOG.info("Total users found :: " + result.size());
            result.forEach(row -> {
                UserDto user = new UserDto();
                user.setId((Long) row[0]);
                user.setFirstName((String) row[1]);
                user.setLastName((String) row[2]);
                user.setMaidenName((String) row[3]);
                user.setEmail((String) row[4]);
                user.setPhone((String) row[5]);
                user.setCompanyName((String) row[6]);
                user.setAge((Integer) row[7]);
                user.setSsn((String) row[8]);
                users.add(user);
            });
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        LOG.info("Users found !!");
        return users;
    }

    @Override
    public List<UserDto> findByFirstName(String firstName) {
        LOG.info("In findByFirstName()");
        List<UserDto> list = new ArrayList<>();
        Optional<List<Object[]>> users = Optional.empty();
        try {
            users = userRepository.findUsersByFirstName(firstName);
            if (users.isPresent()) {
                List<Object[]> userList = users.get();
                for (Object[] row : userList) {
                    UserDto user = new UserDto();
                    user.setId((Long) row[0]);
                    user.setId((Long) row[0]);
                    user.setFirstName((String) row[1]);
                    user.setLastName((String) row[2]);
                    user.setMaidenName((String) row[3]);
                    user.setEmail((String) row[4]);
                    user.setPhone((String) row[5]);
                    user.setCompanyName((String) row[6]);
                    user.setAge((Integer) row[7]);
                    user.setSsn((String) row[8]);
                    list.add(user);
                }
            }
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        LOG.info("In findByLastName()");
        List<UserDto> list = new ArrayList<>();
        Optional<List<Object[]>> users = Optional.empty();
        try {
            users = userRepository.findUsersByLastName(lastName);
            if (users.isPresent()) {
                List<Object[]> userList = users.get();
                for (Object[] row : userList) {
                    UserDto user = new UserDto();
                    user.setId((Long) row[0]);
                    user.setId((Long) row[0]);
                    user.setFirstName((String) row[1]);
                    user.setLastName((String) row[2]);
                    user.setMaidenName((String) row[3]);
                    user.setEmail((String) row[4]);
                    user.setPhone((String) row[5]);
                    user.setCompanyName((String) row[6]);
                    user.setAge((Integer) row[7]);
                    user.setSsn((String) row[8]);
                    list.add(user);
                }
            }
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<UserDto> findBySsn(String ssn) {
        LOG.info("In findBySsn()");
        List<UserDto> list = new ArrayList<>();
        Optional<List<Object[]>> users = Optional.empty();
        try {
            users = userRepository.findUsersBySsn(ssn);
            if (users.isPresent()) {
                List<Object[]> userList = users.get();
                for (Object[] row : userList) {
                    UserDto user = new UserDto();
                    user.setId((Long) row[0]);
                    user.setId((Long) row[0]);
                    user.setFirstName((String) row[1]);
                    user.setLastName((String) row[2]);
                    user.setMaidenName((String) row[3]);
                    user.setEmail((String) row[4]);
                    user.setPhone((String) row[5]);
                    user.setCompanyName((String) row[6]);
                    user.setAge((Integer) row[7]);
                    user.setSsn((String) row[8]);
                    list.add(user);
                }
            }
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public UserDto findUserById(Integer id) {
        LOG.info("In findUserById()");
        UserDto userDto = new UserDto();
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                LOG.info("User fetched !!");
                User userObj = user.get();
                userDto.setId(userObj.getId());
                userDto.setFirstName(userObj.getFirstName());
                userDto.setLastName(userObj.getLastName());
                userDto.setMaidenName(userObj.getMaidenName());
                userDto.setEmail(userObj.getEmail());
                userDto.setPhone(userObj.getPhone());
                userDto.setAge(userObj.getAge());
                userDto.setUniversity(userObj.getUniversity());
                userDto.setUserName(userObj.getUsername());
                userDto.setPassword(userObj.getPassword());
                userDto.setBirthDate(userObj.getBirthDate());
                userDto.setImage(userObj.getImage());
                userDto.setBloodGroup(userObj.getBloodGroup());
                userDto.setHeight(userObj.getHeight());
                userDto.setWeight(userObj.getWeight());
                userDto.setEyeColor(userObj.getEyeColor());
                userDto.setHairColor(userObj.getHair().getColor());
                userDto.setHairType(userObj.getHair().getType());
                userDto.setIp(userObj.getIp());
                userDto.setMacAddress(userObj.getMacAddress());
                if (userObj.getAddress() != null) {
                    userDto.setAddress(userObj.getAddress().getAddress());
                    userDto.setCity(userObj.getAddress().getCity());
                    userDto.setState(userObj.getAddress().getState());
                    userDto.setStateCode(userObj.getAddress().getStateCode());
                    userDto.setCountry(userObj.getAddress().getCountry());
                    userDto.setPostalCode(userObj.getAddress().getPostalCode());
                    if (userObj.getAddress().getCoordinates() != null) {
                        userDto.setAddressLat(userObj.getAddress().getCoordinates().getLat());
                        userDto.setAddressLong(userObj.getAddress().getCoordinates().getLng());
                    }
                }
                if (userObj.getBank() != null) {
                    userDto.setCardExpire(userObj.getBank().getCardExpire());
                    userDto.setCardNumber(userObj.getBank().getCardNumber());
                    userDto.setCardType(userObj.getBank().getCardType());
                    userDto.setCurrency(userObj.getBank().getCurrency());
                    userDto.setIban(userObj.getBank().getIban());

                }
                if (userObj.getCompany() != null) {
                    userDto.setCompanyName(userObj.getCompany().getName());
                    userDto.setDepartment(userObj.getCompany().getDepartment());
                    userDto.setTitle(userObj.getCompany().getTitle());
                    if (userObj.getCompany().getAddress() != null) {
                        userDto.setCompanyAddress(userObj.getCompany().getAddress().getAddress());
                        userDto.setCompanyCity(userObj.getCompany().getAddress().getCity());
                        userDto.setCompanyState(userObj.getCompany().getAddress().getState());
                        userDto.setCompanyStateCode(userObj.getCompany().getAddress().getStateCode());
                        userDto.setCompanyCountry(userObj.getCompany().getAddress().getCountry());
                        userDto.setCompanyPostalCode(userObj.getCompany().getAddress().getPostalCode());
                        if (userObj.getCompany().getAddress().getCoordinates() != null) {
                            userDto.setCompanyAddressLat(userObj.getCompany().getAddress().getCoordinates().getLat());
                            userDto.setCompanyAddressLong(userObj.getCompany().getAddress().getCoordinates().getLng());
                        }
                    }
                }
                userDto.setEin(userObj.getEin());
                userDto.setSsn(userObj.getSsn());
                userDto.setUserAgent(userObj.getUserAgent());
                if (userObj.getCrypto() != null) {
                    userDto.setCoin(userObj.getCrypto().getCoin());
                    userDto.setWallet(userObj.getCrypto().getWallet());
                    userDto.setNetwork(userObj.getCrypto().getNetwork());

                }
                userDto.setRole(userObj.getRole());
            } else {
                LOG.info("User not found !!");
                return null;
            }
        } catch (Exception e) {
            LOG.error("Failed to fetch user from external API" + e.getMessage(), e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CircuitBreaker(name = "externalApiCallerCircuitBreaker", fallbackMethod = "fallbackGetAllUsers")
    public Boolean doInitialSetup() {
        Boolean status = false;
        try {
            JSONObject usersFromApi = getDataFromExternalAPI();
            JSONArray usersArray = usersFromApi.getJSONArray("users");
            loadUsersFromExternalAPI(usersArray);
            status = true;
        } catch (Exception e) {
            throw e; 
        }
        return status;
    }

    public Boolean fallbackGetAllUsers(Throwable t) {
        LOG.error("Fallback method for getAllUsers called due to: " + t.getMessage(), t);
        return false;
    }

}
