import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import { UserService } from '../../service/security/user.service';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  constructor(private userService:UserService) {
  }
  form= new FormGroup({
    username:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required]),
    address:new FormControl('',[Validators.required]),
  })

  register() {
    this.userService.signup(
      this.form.value.username,
      this.form.value.password,
      this.form.value.address
    ).subscribe(response => {
      console.log(response);
    }, error =>{
      console.log(error)
      console.log(error?.error?.message)
    })
  }
}
