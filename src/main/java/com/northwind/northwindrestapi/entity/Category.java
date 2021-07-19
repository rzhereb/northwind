package com.northwind.northwindrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@Data
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CategoryID", nullable = false)
  private int id;

  @Column(name = "CategoryName")
  private String name;

  @Lob
  @Column(name = "Description", length = 512)
  private String description;

  @Lob
  @Column(name = "Picture")
  @JsonIgnore
  private byte[] picture;

}
