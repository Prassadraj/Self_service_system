import { Routes, RouterModule } from "@angular/router";

import { AuthGuard } from "./_helpers";
import * as component from "./component";

const routes: Routes = [
  { path: "", component: component.HomeComponent, canActivate: [AuthGuard] },
  { path: "login", component: component.LoginComponent },
  { path: "register", component: component.RegisterComponent },
  { path: "resetPswd", component: component.ResetPswdComponent },

  // otherwise redirect to home
  { path: "**", redirectTo: "" },
];

export const appRoutingModule = RouterModule.forRoot(routes);
