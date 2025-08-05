import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';  // <-- import here
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { Header } from './components/header/header';
import {routes} from './app.routes';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppComponent,
    Header,
    HomeComponent,
    AboutComponent// <-- add here
  ],
  providers: []
})
@NgModule({
  imports:[
    RouterModule.forRoot(routes),
  ]
})
export class AppModule { }
