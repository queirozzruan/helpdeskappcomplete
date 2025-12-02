import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./auth/auth.guard";

import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/login/login.component";
import { NavComponent } from "./components/nav/nav.component";

import { TecnicoListComponent } from "./components/tecnico/tecnico-list/tecnico-list.component";
import { TecnicoCreateComponent } from "./components/tecnico/tecnico-create/tecnico-create.component";
import { TecnicoUpdateComponent } from "./components/tecnico/tecnico-update/tecnico-update.component";
import { TecnicoDeleteComponent } from "./components/tecnico/tecnico-delete/tecnico-delete.component";

import { ClienteListComponent } from "./components/cliente/cliente-list/cliente-list.component";
import { ClienteCreateComponent } from "./components/cliente/cliente-create/cliente-create.component";
import { ClienteUpdateComponent } from "./components/cliente/cliente-update/cliente-update.component";
import { ClienteDeleteComponent } from "./components/cliente/cliente-delete/cliente-delete.component";

import { ChamadoListComponent } from "./components/chamado/chamado-list/chamado-list.component";
import { ChamadoCreateComponent } from "./components/chamado/chamado-create/chamado-create.component";
import { ChamadoUpdateComponent } from "./components/chamado/chamado-update/chamado-update.component";
import { ChamadoReadComponent } from "./components/chamado/chamado-read/chamado-read.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },

  {
    path: "",
    component: NavComponent,
    canActivate: [AuthGuard],
    children: [
      // HOME (somente técnico e admin)
      {
        path: "home",
        component: HomeComponent,
        data: { roles: ["ADMIN", "TECNICO"] },
      },

      // TÉCNICOS (somente técnico e admin)
      {
        path: "tecnicos",
        component: TecnicoListComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "tecnicos/create",
        component: TecnicoCreateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "tecnicos/update/:id",
        component: TecnicoUpdateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "tecnicos/delete/:id",
        component: TecnicoDeleteComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },

      // CLIENTES (somente técnico e admin)
      {
        path: "clientes",
        component: ClienteListComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "clientes/create",
        component: ClienteCreateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "clientes/update/:id",
        component: ClienteUpdateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "clientes/delete/:id",
        component: ClienteDeleteComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },

      // CHAMADOS (TODOS: admin, técnico, cliente)
      {
        path: "chamados",
        component: ChamadoListComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO", "CLIENTE"] },
      },
      {
        path: "chamados/create",
        component: ChamadoCreateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO", "CLIENTE"] },
      },
      {
        path: "chamados/update/:id",
        component: ChamadoUpdateComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO"] },
      },
      {
        path: "chamados/read/:id",
        component: ChamadoReadComponent,
        canActivate: [AuthGuard],
        data: { roles: ["ADMIN", "TECNICO", "CLIENTE"] },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
