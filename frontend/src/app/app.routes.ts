import { Routes } from '@angular/router';
import { SearchComponent } from './features/search/search';
import { DetailComponent } from './features/detail/detail';

export const routes: Routes = [
  { path: '', redirectTo: 'search', pathMatch: 'full' },
  { path: 'search', component: SearchComponent },
  { path: 'image/:id', component: DetailComponent }
];
