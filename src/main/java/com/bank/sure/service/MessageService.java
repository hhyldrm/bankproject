package com.bank.sure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.bank.sure.domain.Message;
import com.bank.sure.exception.ResourceNotFoundException;
import com.bank.sure.exception.message.ExceptionMessage;
import com.bank.sure.repository.MessageRepository;

import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForUnnamedType;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public void createMessage(Message message) {
		messageRepository.save(message);
	}
	
	public List<Message> getAll() {
		return messageRepository.findAll();
	}

	public Message getMessage(Long id) {
		
	return messageRepository.findById(id).
			orElseThrow(()-> new ResourceNotFoundException(String.format(ExceptionMessage.MESSAGE_NOT_FOUND_MESSAGE, id)));
	}

	public void deleteMessage(Long id) {
		Message message= messageRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException(String.format(ExceptionMessage.MESSAGE_NOT_FOUND_MESSAGE, id)));
		messageRepository.deleteById(message.getId());
		
	}

	public void updateMessage(Long id, Message message) {
		Message foundMessage=getMessage(id);
		
		foundMessage.setName(message.getName());
		foundMessage.setSubject(message.getSubject());
		foundMessage.setBody(message.getBody());
		foundMessage.setEmail(message.getEmail());
		foundMessage.setPhoneNumber(message.getPhoneNumber());
		
		messageRepository.save(foundMessage);
		
	}
	
}
