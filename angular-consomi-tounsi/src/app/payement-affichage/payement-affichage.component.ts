import { Component, OnInit } from '@angular/core';
import { Payement } from 'src/app/models/payement';
import { User } from '../models/user';
import { Product } from 'src/app/models/product';
import { ProductService } from '../product.service';
import { CategoryProduit } from '../models/categoryProduit';

@Component({
  selector: 'app-payement-affichage',
  templateUrl: './payement-affichage.component.html',
  styleUrls: ['./payement-affichage.component.css']
})
export class PayementAffichageComponent implements OnInit {
  listPayement: Payement[];
  //payement: Payement=new Payement(0,new Date(),0,0,"","","",new User(0,"","","",0,"","",0,"","","",""),new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0)) ;
  constructor(private service:ProductService) { }

  ngOnInit( ): void {
   
    this.service.getPayement().subscribe(
    (data:Payement[])=>this.listPayement=data );
  }
  public ConfirmerPayement(p:Payement){
    let resp= this.service.confirmerPayement(p);
    resp.subscribe((data:Payement[])=>this.listPayement=data);
   }

}
