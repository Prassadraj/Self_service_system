import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";

import { AlertService, UserService } from "@/_services";

@Component({ templateUrl: "register.component.html" })
export class RegisterComponent implements OnInit {
  setupForm: FormGroup;
  submitted = false;
  allQuestions = [];
  loading: boolean;
  isRegistration = false;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private alertService: AlertService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.isRegistration = window.location.href.includes("/register")
      ? true
      : false;
    this.setupForm = this.fb.group({
      id: this.fb.control(""),
      answerDto: this.fb.array([]),
      firstName: this.fb.control("", [Validators.required]),
      lastName: this.fb.control("", [Validators.required]),
      mobileNo: this.fb.control("", [Validators.required]),

      userId: this.fb.control("", [Validators.required]),
      password: this.fb.control("", [Validators.required]),
      confirmPswd: this.fb.control("", [Validators.required]),
    });
    this.getAllQuestions();
  }
  inItForm(questions) {
    return this.fb.group({
      questionId: [questions?.questionId ?? ""],
      question: [questions?.question ?? ""],
      answer: [questions?.answer ?? ""],
      id: [questions?.id ?? ""],
    });
  }
  getAllQuestions() {
    this.userService.getAllQuestions().subscribe((res: any) => {
      if (res) {
        res.forEach((element) => {
          let control = this.setupForm.get("answerDto") as FormArray;
          control.push(this.inItForm(element));
        });
      }
    });
  }
  get getFormControls() {
    const control = this.setupForm.get("answerDto") as FormArray;
    return control;
  }
  confirmPswd() {
    let pswd = this.setupForm.get("password").value;
    let conPswd = this.setupForm.get("confirmPswd").value;
    if (pswd === conPswd) {
      return true;
    } else {
      alert("Enter correct password.");
      this.setupForm.get("confirmPswd").reset();
    }
  }
  submitForm() {
    this.submitted = true;
    this.loading = true;
    console.log(this.setupForm.getRawValue());
    let postData = {
      ...this.setupForm.getRawValue(),
    };

    this.userService.register(postData).subscribe(
      (res) => {
        this.alertService.success(
          `${
            this.isRegistration
              ? "Registration successful."
              : "Password reset successfully."
          }`,
          true
        );
        this.router.navigate(["/login"]);
      },
      (error) => {
        this.alertService.error(error);
        this.loading = false;
      }
    );
  }
}
