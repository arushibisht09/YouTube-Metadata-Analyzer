package com.YoutubeTools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.YoutubeTools.service.ThumbnailService;

@Controller
public class ThumbnailController {

    @Autowired
    ThumbnailService service;

    @GetMapping("/thumbnail")
    public String getThumbnail(){
        return "thumbnails";
    }


    @PostMapping("/get-thumbnail")
    public String showThumbnail(@RequestParam ("videoUrlOrId") String videoUrlOrId, Model model){
        String videoId=service.extractVideoId(videoUrlOrId);
        if(videoId==null){
            model.addAttribute("error","Invalid YouTube URL");
            return "thumbnails";
        }

        String thumbnailUrl="https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
        // frontend pe send krne keliye model mei pack krke bhejna h
        model.addAttribute("thumbnailUrl",thumbnailUrl);
        return "thumbnails";
    }
}