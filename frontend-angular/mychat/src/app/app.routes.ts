import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },         // http://localhost/
  { path: 'about', component: AboutComponent },   // http://localhost/about
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // redirect '' to 'home'
  { path: '**', redirectTo: 'home' }
];
