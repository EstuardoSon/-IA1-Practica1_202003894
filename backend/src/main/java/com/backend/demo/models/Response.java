package com.backend.demo.models;

public class Response {
	public int code;
	public String msg;
	public Response(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
