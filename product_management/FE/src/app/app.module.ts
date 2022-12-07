import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {ProductModule} from './controller/product/product.module';
import {AppRoutingModule} from './app-routing.module';
import {SecurityModule} from './controller/security/security.module';
import { HeaderComponent } from './controller/header/header.component';
import { FooterComponent } from './controller/footer/footer.component';
import {GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule} from 'angularx-social-login';

const googleLoginOptions = {
  scope: 'profile email',
  plugin_name: 'login'
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
    ProductModule,
    AppRoutingModule,
    SecurityModule,
    SocialLoginModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '612774287153-uthnsrl25on17doe8413il68ebv9c969.apps.googleusercontent.com',
              googleLoginOptions
            )
          },
        ]
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
