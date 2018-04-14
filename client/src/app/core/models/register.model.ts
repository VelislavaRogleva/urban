export class RegisterModel {
  username: String;
  password: String;
  email: String;


  constructor(username: String, email: String, password: String) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}
