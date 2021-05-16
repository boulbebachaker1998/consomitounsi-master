import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CategoryProduit } from '../models/categoryProduit';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  product: Product=new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0) ;
  quantitec:number;
  payementType:string;
  
  
  constructor(private service:ProductService) { }

  ngOnInit(): void {
    this.product=history.state;
  }
  public addPayement(){
    let resp=this.service.doBuy(this.product,this.quantitec,this.payementType);
    resp.subscribe((data)=>console.log(data))}

   
}
