import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ChambreComponent } from './components/chambre/chambre.component';
import { DetailChambreComponent } from './components/detail-chambre/detail-chambre.component';
import { DetailResComponent } from './components/detail-res/detail-res.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './auth-guard.guard';
import { RegisterComponent } from './components/register/register.component';
import { MonCompteComponent } from './components/mon-compte/mon-compte.component';
import { ReserverComponent } from './components/reserver/reserver.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';
import { AdminAuthGuard } from './admin-auth.guard';
import { ClientsComponent } from './admin/clients/clients.component';
import { ReservationsComponent } from './admin/reservations/reservations.component';
import { ChambresComponent } from './admin/chambres/chambres.component';
import { OffresComponent } from './admin/offres/offres.component';
import { EvenementsComponent } from './admin/evenements/evenements.component';
import { ContactComponent } from './components/contact/contact.component';
import { AproposComponent } from './components/apropos/apropos.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'reserver', component: ReservationComponent },
    { path: 'chambres', component: ChambreComponent },
    { path: 'chambre/:id', component: DetailChambreComponent },
    { path: 'chambree/:id', component: DetailResComponent },
    { path: 'login', component: LoginComponent },
    { path: 'reservation', component: ReservationComponent, canActivate: [AuthGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'compte', component: MonCompteComponent },
    { path: 'reserver2/:numero', component: ReserverComponent },
    { path: 'admin-login', component: LoginAdminComponent },
    { path: 'admin', component: DashboardComponent, canActivate: [AdminAuthGuard] }, 
    { path: 'admin/clients', component: ClientsComponent }, // Clients
    { path: 'admin/reservations', component: ReservationsComponent }, // Réservations
    { path: 'admin/chambress', component: ChambresComponent }, // Chambres
    { path: 'admin/offres', component: OffresComponent }, // Offres
    { path: 'admin/evenements', component: EvenementsComponent }, // Evenements
    { path: 'apropos', component: AproposComponent }, // Evenements
    { path: 'contact', component: ContactComponent }, // Evenements

];
