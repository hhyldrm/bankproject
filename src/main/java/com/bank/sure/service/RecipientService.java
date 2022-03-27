package com.bank.sure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.sure.controller.request.RecipientRequest;
import com.bank.sure.domain.Account;
import com.bank.sure.domain.Recipient;
import com.bank.sure.domain.User;
import com.bank.sure.exception.ConflictException;
import com.bank.sure.exception.ResourceNotFoundException;
import com.bank.sure.exception.message.ExceptionMessage;
import com.bank.sure.repository.RecipientRepository;

@Service
public class RecipientService {
	
	@Autowired
	private RecipientRepository recipientRepository;
	
	@Autowired
	AccountService accountService;
	
	public void addRecipient(RecipientRequest recipientRequest,User user) {
			Account account=accountService.findByAccountNumber(recipientRequest.getAccountNumber());
			
			if(user.getId().equals(account.getUser().getId())) {
				throw new ConflictException(ExceptionMessage.RECIPIENT_ADD_ERROR_MESSAGE);
			}
			
			
//			Boolean isExist = recipientRepository.existsByUserAndAccount(user, account);
//			
//			if(isExist) {
//				throw new ConflictException(ExceptionMessage.RECIPIENT_DUPLICATE_MESSAGE);
//			}
			
			
			Optional<Recipient> foundRecipient = recipientRepository.findRecipientByUserAndAccountId(user.getId(), account.getId());
			
			if(foundRecipient.isPresent()) {
				throw new ConflictException(ExceptionMessage.RECIPIENT_DUPLICATE_MESSAGE);
			}
			
			
			validateRecipient(recipientRequest,account);
			
			Recipient recipient=new Recipient();
			recipient.setUser(user);
			recipient.setAccount(account);
			
			recipientRepository.save(recipient);
	}
	
	
	private void validateRecipient(RecipientRequest recipientRequest,Account account) {
		String name=account.getUser().getFirstName()+" "+account.getUser().getLastName();
		
		if(!name.equalsIgnoreCase(recipientRequest.getName())) {
			throw new ResourceNotFoundException(ExceptionMessage.RECIPIENT_VALIDATION_ERROR_MESSAGE);
		}
	}
	
	
	public void removeRecipient(User user,Long id) {
			Recipient recipient=recipientRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException(String.format(ExceptionMessage.RECIPIENT_NOT_FOUND_MESSAGE,id) ));

			if(user.getId().equals(recipient.getUser().getId())) {
				recipientRepository.deleteById(recipient.getId());
			}else {
				throw new ConflictException(ExceptionMessage.RECIPIENT_DELETE_PERMISSON_MESSAGE);
			}
			
	}
	

}