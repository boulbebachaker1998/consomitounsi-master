package tn.esprit.consomitounsi.service;

import java.util.List;

import tn.esprit.consomitounsi.modal.Category;

public interface CategoryService {

	Category addCategory(Category category);

	String DeleteCategory(long categoryId);

	Category updateCategory(Category category);

	List<Category> retrieveAllCategory();

	Category findCategoryById(long id);

}