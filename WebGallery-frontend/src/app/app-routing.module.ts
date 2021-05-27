import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {ImagesComponent} from './components/images/images.component'
import {CreateImageComponent} from "./components/create-image/create-image.component";

const routes: Routes = [
  {path: 'listImages', component: ImagesComponent},
  {path: 'createImage', component: CreateImageComponent},
  {path: 'edit/:id', component: CreateImageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponents = [ImagesComponent];
