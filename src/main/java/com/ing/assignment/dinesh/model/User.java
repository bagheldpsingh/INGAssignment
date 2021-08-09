package com.ing.assignment.dinesh.model;

import lombok.*;


/**
 * This class is responsible for creating user for bank accounts.
 */

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
     private String usename;
     private String password;
}
