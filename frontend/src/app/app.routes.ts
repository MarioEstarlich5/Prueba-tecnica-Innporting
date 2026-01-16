import { Routes } from '@angular/router';
import { SearchComponent } from './features/search/search';

export const routes: Routes = [
  { path: '', redirectTo: 'search', pathMatch: 'full' },
  { path: 'search', component: SearchComponent }
];
