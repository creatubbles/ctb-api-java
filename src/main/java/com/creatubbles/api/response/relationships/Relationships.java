package com.creatubbles.api.response.relationships;

import lombok.Value;

@Value
public class Relationships {

    RelationshipUser user;
    RelationshipUser[] creators;
    
}
