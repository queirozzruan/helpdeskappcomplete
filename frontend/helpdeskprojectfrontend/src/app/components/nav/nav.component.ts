import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";
import { ToastrService } from "ngx-toastr";
import { MatDrawer } from "@angular/material/sidenav";

@Component({
  selector: "app-nav",
  templateUrl: "./nav.component.html",
  styleUrls: ["./nav.component.css"],
})
export class NavComponent implements OnInit {
  @ViewChild("drawer") drawer: MatDrawer;

  userRole: string | null = null;

  // Injeção de dependências

  constructor(
    private router: Router,
    private authService: AuthService,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {
    this.userRole = this.authService.getRole();
    this.router.navigate(["home"]);
  }

  isCliente(): boolean {
    return this.userRole === "CLIENTE";
  }

  logout() {
    this.router.navigate(["login"]);
    this.authService.logout();
    this.toast.info("Logout realizado com sucesso", "Logout", {
      timeOut: 7000,
    });
  }
}
