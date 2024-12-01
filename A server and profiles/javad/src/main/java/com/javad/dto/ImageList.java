package com.javad.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;



public class ImageList {
private List<Integer> list;

public List<Integer> getList() {
	return list;
}

public void setList(List<Integer> list) {
	this.list = list;
}

}
