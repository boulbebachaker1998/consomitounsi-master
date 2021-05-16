import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Product } from 'src/app/models/product';
import { Payement } from './models/payement';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  url='http://localhost:8081/add-Produit';
  appUrl='http://localhost:8081/';
  
  constructor(private http:HttpClient) { }

  public doRegistration(p:Product){
    return this.http.post(this.url,p,{responseType:'text' as 'json'});
  }
  public getProduct(){
    return this.http.get<Product[]>('http://localhost:8081/retrieve-all-Produit');
  }
  public getProductByname(nomProduit){
    return this.http.get(this.appUrl+"search/"+nomProduit);
  }

  public deleteProduct(id){
    return this.http.delete(this.appUrl+"remove-produit/"+id);
  }
  public doModification(p:Product){
    return this.http.post("http://localhost:8081/modify-Produit",p,{responseType:'text' as 'json'});
  }
  public getPayement(){
    var user= JSON.parse(window.sessionStorage.getItem("auth-user"));
    let httpParams = new HttpParams().set('userId',user.id);
    console.log(httpParams.toString());
    return this.http.get<Payement[]>('http://localhost:8081/retrieve-all-Payements',{params: httpParams});
  }
  public doBuy(p:Product,quantity: number,payementType : string){
    const formData = new FormData();
    formData.append('productId', p.idProduit+'');
    formData.append('quantity',quantity+'');
    formData.append('payementType', payementType+'');
    
    
    return this.http.post("http://localhost:8081/buy",formData);
  }
  public getBestProduct(){
    return this.http.get<Product[]>('http://localhost:8081/retrieve-best-Produit');
  }

  public confirmerPayement(p:Payement){
    const formData = new FormData();
    formData.append('payementId', p.id+'');

    return this.http.post('http://localhost:8081/purchase',formData);
  }

  public fileUpload(imgaeFile: File,identifier : number) {
    const formData = new FormData();
    console.log(imgaeFile.size);
    formData.append('file', imgaeFile);
    formData.append('identifier', identifier+'');
    return this.http.post("http://localhost:8081/file",formData);
  }

  public voteProduct(idp:number,userid:number,voteid:number) {
    const formData = new FormData();
    return this.http.post("http://localhost:8081/add-votelike/"+idp+"/"+userid+"/"+voteid,formData);
  }

}
