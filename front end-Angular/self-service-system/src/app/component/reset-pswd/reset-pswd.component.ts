import { AlertService, AuthenticationService, UserService } from "@/_services";
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: "app-reset-pswd",
  templateUrl: "reset-pswd.component.html",
})
export class ResetPswdComponent implements OnInit {
  setupForm: FormGroup;
  submitted = false;
  allQuestions = [];
  loading: boolean;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private alertService: AlertService,
    private router: Router,
    private authService: AuthenticationService
  ) {}
  ngOnInit(): void {
    this.setupForm = this.fb.group({
      id: this.fb.control(""),
      answerDto: this.fb.array([]),
password: this.fb.control(''),
      userId: this.fb.control("", [Validators.required]),
    });
    this.getAllQuestions();
  }
  inItForm(questions) {
    return this.fb.group({
      questionId: [questions?.id ?? ""],
      question: [questions?.question ?? ""],
      answer: [questions?.answer ?? ""],
      id: [""],
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

    this.userService.resetPswd(postData).subscribe(
      (res) => {
        if (res?.userId){
        this.authService.currentUser = res;
        this.alertService.success("Password reset successfully.", true);
        this.router.navigate(["/login"], {queryParams: {isRecovered: true}});
      }
      },
      (error) => {
        this.alertService.error(error);
        this.loading = false;
      }
    );
  }
}
