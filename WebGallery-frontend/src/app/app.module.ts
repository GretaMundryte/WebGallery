import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {HttpClientModule} from "@angular/common/http";
import {MatChipsModule} from "@angular/material/chips";
import {MatIconModule} from "@angular/material/icon";
import {AppRoutingModule, routingComponents} from "./app-routing.module";
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {ButtonComponent} from './components/button/button.component';
import {ImagesComponent} from './components/images/images.component';
import {ImageItemComponent} from './components/image-item/image-item.component';
import {AddImageComponent} from './components/add-image/add-image.component';
import {CreateImageComponent} from './components/create-image/create-image.component';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {FilterPipe} from './filter.pipe';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ButtonComponent,
    ImagesComponent,
    ImageItemComponent,
    AddImageComponent,
    routingComponents,
    CreateImageComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    MatSelectModule,
    FontAwesomeModule,
    HttpClientModule,
    AppRoutingModule,
    NoopAnimationsModule,
    MatChipsModule,
    MatFormFieldModule,
    MatIconModule,
    MatOptionModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatAutocompleteModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
