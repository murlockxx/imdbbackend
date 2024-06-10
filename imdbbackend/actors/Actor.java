package com.imdbv1.imdbbackend.actors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Actor {
    

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String surname;
        private String nickName;
        private String photoUrl;





        
        public Actor(Long id, String name, String surname, String nickName, String photoUrl) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.nickName = nickName;
            this.photoUrl = photoUrl;
        }
        @Override
        public String toString() {
            return "Actor [id=" + id + ", name=" + name + ", surname=" + surname + ", nickName=" + nickName
                    + ", photoUrl=" + photoUrl + "]";
        }
        public Actor() {
        }
       
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSurname() {
            return surname;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }
        public String getNickName() {
            return nickName;
        }
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
        public String getPhotoUrl() {
            return photoUrl;
        }
        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }
}