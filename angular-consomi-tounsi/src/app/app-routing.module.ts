import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { ListStocksComponent } from './components/list-stocks/list-stocks.component';
import { ListInvoicesComponent } from './components/list-invoices/list-invoices.component';
import { ListRayonsComponent } from './components/list-rayons/list-rayons.component';
import { AddInvoiceComponent } from './components/add-invoice/add-invoice.component';
import { AddStockComponent } from './components/add-stock/add-stock.component';
import { AddRayonComponent } from './components/add-rayon/add-rayon.component';
import { AdminProductsComponent } from './admin-products/admin-products.component';
import { AjouterProduitComponent } from './ajouter-produit/ajouter-produit.component';
import { UpdateComponent } from './update/update.component';
import { ClientProductsComponent } from './client-products/client-products.component';
import { PayementAffichageComponent } from './payement-affichage/payement-affichage.component';
import { BuyComponent } from './buy/buy.component';
import { BestProductsComponent } from './best-products/best-products.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'admin', component: BoardAdminComponent },
  {path: 'invoices', component: ListInvoicesComponent},
  {path: 'addinvoice', component: AddInvoiceComponent},
  {path: 'editinvoice/:id', component: AddInvoiceComponent},

  {path: 'AdminProducts', component: AdminProductsComponent},
  {path: 'ajouter', component: AjouterProduitComponent},
  {path: 'update', component: UpdateComponent},
  {path: 'ClientProducts', component: ClientProductsComponent},
  {path:"payement",component:PayementAffichageComponent },
   {path:"buy",component:BuyComponent },
   {path:"bestProduct",component:BestProductsComponent },


  {path: 'rayons', component: ListRayonsComponent},
  {path: 'rayon/add', component: AddRayonComponent},
  {path: 'rayon/update/:id', component: AddRayonComponent},

  {path: 'stocks', component: ListStocksComponent},
  {path: 'stock/add', component: AddStockComponent},
  {path: 'stock/update/:id', component: AddStockComponent},
  
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }