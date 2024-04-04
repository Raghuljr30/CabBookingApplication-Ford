import { ApplicationConfig } from '@angular/core';
import { provideRouter,Router,RouterLink,RouterLinkActive } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),provideHttpClient(),Router,RouterLink,RouterLinkActive]
};
