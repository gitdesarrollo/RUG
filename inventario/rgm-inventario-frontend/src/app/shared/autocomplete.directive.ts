import { Directive, HostListener, Input, OnInit, Renderer2, ElementRef } from "@angular/core";

@Directive({
  selector: '[betterAutocomplete]'
})
export class AutocompleteDirective implements OnInit {
  // @Input() property: string;
  property: string;
  dataList: any[];
  callback: any;

  constructor(private elementRef: ElementRef,
    private renderer: Renderer2) {}

  ngOnInit() {
  }

  @Input() set data(newData: any) {
    if (newData) {
      this.dataList = newData;
    }
  }

  @Input() set init(newInit: any) {
    this.callback = newInit.onAutocomplete;
    this.property = newInit.property;
    // this.dataList = newInit.data;
    // console.log(newInit);
  }

  @HostListener('keyup', ['$event']) filtering(e) {
    let parent = this.renderer.parentNode(this.elementRef.nativeElement);
    this.clearList(parent, this.elementRef.nativeElement);
    if (this.elementRef.nativeElement.value) {
      let encontrados = this.dataList.filter(o => o[this.property].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, "").indexOf(this.elementRef.nativeElement.value.toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, "")) > -1);
      let list = this.renderer.createElement('ul');
      this.renderer.addClass(list, 'autocomplete-content');
      this.renderer.addClass(list, 'dropdown-content');
      encontrados.forEach(o => {
        let li = this.renderer.createElement('li');
        this.renderer.listen(li, 'mousedown', (event) => {
          event.preventDefault();
        });
        this.renderer.listen(li, 'click', (event) => {
          // eliminar lista
          this.clearList(parent, this.elementRef.nativeElement);
          // asignar texto a input
          this.elementRef.nativeElement.value = o[this.property];
          // invocar callback
          this.callback(o);
        });
        let span = this.renderer.createElement('span');
        let text = this.renderer.createText(o[this.property]);
        this.renderer.appendChild(span, text);
        this.renderer.appendChild(li, span);
        this.renderer.appendChild(list, li);
      });
      this.renderer.appendChild(parent, list);
    }
  }

  @HostListener('blur') onBlur() {
    let parent = this.renderer.parentNode(this.elementRef.nativeElement);
    this.clearList(parent, this.elementRef.nativeElement);
  }

  private clearList(parent, element) {
    // quitar listas anteriores
    let oldList = this.renderer.nextSibling(element);
    if (oldList) {
      this.renderer.removeChild(parent, oldList);
    }
  }
}
