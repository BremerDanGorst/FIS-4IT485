package com.example.fis4it485.controller;

import com.example.fis4it485.service.MessageTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageTransformationController {
	private final MessageTransformationService messageTransformationService;


	@PostMapping("/test")
	public String test(@RequestBody String input) {
		return messageTransformationService.transformMessage(input);
	}

}
