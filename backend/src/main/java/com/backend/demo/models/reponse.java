package com.backend.demo.models;

import java.util.List;

public class reponse {
	public int code;
	public List<tag> tags;
	public String msg;
	public int rostros;
	
	public reponse(int code, List<tag> tags, int rostros) {
		super();
		this.code = code;
		this.tags = tags;
		this.rostros = rostros;
		this.msg = "Correcto";
	}
	
	public reponse(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
