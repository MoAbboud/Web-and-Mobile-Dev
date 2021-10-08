import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../api.service';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent implements OnInit {

  bookForm: FormGroup;
  isbn: string = '';
  title: string = '';
  description: string = '';
  author: string = '';
  publisher: string = '';
  published_year: string = '';
  bookid: string = '';

  book = {};

  constructor(private route: ActivatedRoute, private router: Router, private api: ApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.getBookDetails(this.route.snapshot.params['id']);
    this.bookid = this.route.snapshot.params['id'];
    console.log(this.bookid);
    this.bookForm = this.formBuilder.group({
      'isbn': [null, Validators.required],
      'title': [null, Validators.required],
      'description': [null, Validators.required],
      'author': [null, Validators.required],
      'publisher': [null, Validators.required],
      'published_year': [null, Validators.required]
    });
  }

  onFormSubmit(form: NgForm) {
    this.api.updateBook(this.bookid, form)
      .subscribe(res => {
        let id = res['_id'];
        console.log(id + "---");
        this.router.navigate(['/book-details', id]);
      }, (err) => {
        console.log(err);
      });
  }

  getBookDetails(id) {
    this.api.getBook(id)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      });
  }
}
