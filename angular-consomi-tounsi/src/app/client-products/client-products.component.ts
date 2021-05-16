import { Component, OnInit , TemplateRef } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from '../product.service';
import { CategoryProduit } from '../models/categoryProduit';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-client-products',
  templateUrl: './client-products.component.html',
  styleUrls: ['./client-products.component.css']
})
export class ClientProductsComponent implements OnInit {

  listProduct: Product[];
  product: Product=new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0) ;
  nom:string;
  voted:boolean;
  voteTop:string;
  voteleft:string;
  voteRes:any
  constructor(private service:ProductService,private router : Router) {this.voted=false; }

  ngOnInit(): void {this.service.getProduct().subscribe(
    (data:Product[])=>this.listProduct=data );
  }
  public findProductByName(){
    let resp= this.service.getProductByname(this.nom);
    resp.subscribe((data:Product[])=>this.listProduct=data);
   }
   public buyproduit(produit:Product ){
    this.router.navigateByUrl('/buy', { state: produit});
  }
 
  public startVote(event,produit : Product){
    this.product=produit;
    this.voteTop = event.pageY+'px';
   this. voteleft = event.pageX+'px';
    this.voted=true;
  }

  public vote(nb:number){
   var user= JSON.parse(window.sessionStorage.getItem("auth-user"));
console.log(user.id);
    let resp= this.service.voteProduct(this.product.idProduit,user.id,nb);
    resp.subscribe((data:any)=>this.voteRes=data);

    this.voted=false;
  }

}
