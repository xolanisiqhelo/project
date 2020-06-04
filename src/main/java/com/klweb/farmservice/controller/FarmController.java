package com.klweb.farmservice.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klweb.farmservice.model.Farm;
import com.klweb.farmservice.model.User;
import com.klweb.farmservice.dto.CreateFarmDto;
import com.klweb.farmservice.dto.UpdateFarmDto;
import com.klweb.farmservice.mapper.SelmaMapper;
import com.klweb.farmservice.model.ErrorMessage;
import com.klweb.farmservice.repo.FarmRepository;
import com.klweb.farmservice.repo.UserRepository;

import fr.xebia.extras.selma.Selma;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(path="/api/farm")
@Api(value = "Farm Service", tags = {"Farm Service"}, produces = "application/json", consumes = "application/json")
public class FarmController {
	@Autowired private FarmRepository farmRepo;
	@Autowired private UserRepository userRepo;
	
	SelmaMapper mapper = Selma.builder(SelmaMapper.class).build();
	
	// Add user to new Farm
	@PostMapping(path="user/{user_id}", consumes = "application/json")
	public ResponseEntity<Object> createFarm(
			@RequestBody(required = true) CreateFarmDto createFarm,
			@PathVariable(name="user_id", required = true) String user_id){
		
		try {
			// get user
			Optional<User> user = userRepo.findById(Long.parseLong(user_id));
							
			if(!user.isPresent()) {
				ErrorMessage<String> message = new ErrorMessage<String>("User not found");				
				return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
			}else {
				// save farm
				Farm farm = mapper.asFarm(createFarm);
				
				Farm savedFarm = farmRepo.save(farm);				
				
	            // add farm to the user
	            user.get().getFarms().add(farm);

	            // update the user
	            userRepo.saveAndFlush(user.get());
	            
	            Optional<Farm> updatedFarm = farmRepo.findById(savedFarm.getId());
	            return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
			}
					
		}catch(Exception e) {
			ErrorMessage<String> message = new ErrorMessage<String>(e.getMessage());
			return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// Add user to existing farm
	@PostMapping(path="/{farm_id}/user/{user_id}", produces = "application/json")
	public ResponseEntity<Object> addUserToExistingFarm(
			@PathVariable(name="farm_id", required = true) String farm_id,
			@PathVariable(name="user_id", required = true) String user_id){
		
		try {
			// get user
			Optional<User> user = userRepo.findById(Long.parseLong(user_id));
							
			if(!user.isPresent()) {
				ErrorMessage<String> message = new ErrorMessage<String>("User not found");				
				return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
			}else {
				Optional<Farm> farm = farmRepo.findById(Long.parseLong(farm_id));
				
				if(!farm.isPresent()) {
					ErrorMessage<String> message = new ErrorMessage<String>("Farm not Found");				
					return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
				}
				
				// if user already belongs to the farm check
//				farm.get().getUsers().forEach(owner -> {
//					if(owner.getId() == Long.parseLong(user_id)) {
//						found = true;
//					}
//				});
//				
//				if(found) {
//					ErrorMessage<String> message = new ErrorMessage<String>("User already belongs to the farm");				
//					return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
//				}
				
				// add farm to user
				user.get().getFarms().add(farm.get());

	            // update the user
	            userRepo.saveAndFlush(user.get());
	            
	            Optional<Farm> updatedFarm = farmRepo.findById(Long.parseLong(farm_id));
	            
	            return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
			}
					
		}catch(Exception e) {
			ErrorMessage<String> message = new ErrorMessage<String>(e.getMessage());
			return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(path="/{farm_id}", produces = {"application/json"})
	public ResponseEntity<Object> getFarm(@PathVariable(name = "farm_id", required = true) String id){
		try {
			Optional<Farm> farm = farmRepo.findById(Long.parseLong(id));
			
			if(!farm.isPresent()) {
				ErrorMessage<String> message = new ErrorMessage<String>("Farm not found");				
				return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(farm, HttpStatus.OK);
			}
			
		}
		catch(Exception e) {
			ErrorMessage<String> message = new ErrorMessage<String>(e.getMessage());
			return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping(path="/{farm_id}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<Object> updateFarm(
			@RequestBody(required = true) UpdateFarmDto newFarm,
			@PathVariable(name="farm_id", required = true) String id
			){
		try {
			Optional<Farm> farm = farmRepo.findById(Long.parseLong(id));
			
			if(!farm.isPresent()) {
				ErrorMessage<String> message = new ErrorMessage<String>("Farm not found");		
				return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
			}else {
				Farm updatedFarm = mapper.updateFarm(newFarm, farm.get());
				
				// save updated farm
				updatedFarm = farmRepo.save(updatedFarm);
				
				return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
			}
		}catch(Exception e) {
			ErrorMessage<String> message = new ErrorMessage<String>(e.getMessage());
			return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	@DeleteMapping(path="/{farm_id}/user/{user_id}")
//	public ResponseEntity<Object> deleteFarm(
//			@PathVariable(name = "farm_id", required = true) String farm_id,
//			@PathVariable(name = "user_id", required = true) String user_id){
//		try {
//			// get user
//			Optional<User> user = userRepo.findById(Long.parseLong(user_id));
//							
//			if(!user.isPresent()) {
//				ErrorMessage<String> message = new ErrorMessage<String>("User not found");				
//				return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
//			}else {
//				Optional<Farm> farm = farmRepo.findById(Long.parseLong(farm_id));
//				
//				if(!farm.isPresent()) {
//					ErrorMessage<String> message = new ErrorMessage<String>("Farm not Found");				
//					return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
//				}
//				
//				// filter out the user
//				Set<User> newusers = farm.get().getUsers().stream()
//				.filter(owner -> owner.getId() != Long.parseLong(user_id))
//				.collect(Collectors.toSet());
//				
//				farm.get().setUsers(newusers);
//				
//				Farm updatedFarm = farmRepo.save(farm.get());
////				
////				ErrorMessage<String> message = new ErrorMessage<String>("User deleted from farm");
//				
//				return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
//			}		
//			
//		}
//		catch(Exception e) {
//			ErrorMessage<String> message = new ErrorMessage<String>(e.getMessage());
//			return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	
	
}
