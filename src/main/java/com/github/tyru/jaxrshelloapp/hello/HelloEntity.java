package com.github.tyru.jaxrshelloapp.hello;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hello")
@NamedQueries({
	@NamedQuery(name="hello.findAll", query="select h from HelloEntity h"),
	@NamedQuery(name="hello.findOne", query="select h from HelloEntity h where h.lang = :lang"),
	@NamedQuery(name="hello.deleteMsg", query="delete from HelloEntity h where h.lang = :lang")
})
@RequestScoped
@lombok.Data
public class HelloEntity implements Serializable {
	@Id @NotNull
	private String lang;
	@NotNull
	private String msg;
}
