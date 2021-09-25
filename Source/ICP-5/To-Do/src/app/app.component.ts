import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  //Declare the items array for the tasks
  public items = [];

  //newTask value that will bring the information from the input.
  //And also be taking back to be displayed to the user.
  public newTask: string | undefined;

  title: string | undefined;
  //Add task to list
  public addToList() {
    //Only add the task when it is not empty
    if (this.newTask == '') {

    }
    else
    {
      //Add the task to the array.
      // @ts-ignore
      this.items.push(this.newTask);
      this.newTask = '';
    }
  }

  public deleteTask(index: number) {
    //Delete the task from the array at the specified index.
    this.items.splice(index, 1);
  }
}
