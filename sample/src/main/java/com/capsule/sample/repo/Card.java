package com.capsule.sample.repo;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/19 20:51
 */
public class Card {

  private int id;
  private int picRes;
  private String name;
  private String description;

  public Card(int id, int picRes, String name, String description) {
    this.id = id;
    this.picRes = picRes;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPicRes() {
    return picRes;
  }

  public void setPicRes(int picRes) {
    this.picRes = picRes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
