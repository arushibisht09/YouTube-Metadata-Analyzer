package com.YoutubeTools.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.YoutubeTools.model.SearchVideo;
import com.YoutubeTools.model.VideoDetails;
import com.YoutubeTools.service.YouTubeService;


@Controller
@RequestMapping("/youtube")
public class YoutubeTagsController {
	
	@Autowired
	private YouTubeService youTubeService ; 
	
	private boolean isApiConfigured() {
		
		if(apiKey==null || apiKey.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	@Value("${youtube.api.key}")
	private String apiKey ;
	
	
	@PostMapping("/search")
	public String videoTags(@RequestParam("videoTitle") String videoTitle, Model model) {
	
		
		if(!isApiConfigured()) {
			model.addAttribute("error", "API Key is not configured"); 
			return "home"; 
		}
		
		if(videoTitle == null || videoTitle.isEmpty()) {
			model.addAttribute("error", "Input Video Title Required/Invalid") ; 
			return "home" ; 
		}
		
		try {
			SearchVideo result=youTubeService.searchVideos(videoTitle);
            model.addAttribute("primaryVideo",result.getPrimaryVideo());
            model.addAttribute("relatedVideos",result.getRelatedVideos());
            return "home"; 
		}catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "home";
	}
	
}
}
