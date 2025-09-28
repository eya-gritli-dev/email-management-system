import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterReceivers',
  standalone: true
})
export class FilterReceiversPipe implements PipeTransform {
  transform(receivers: any[], searchText: string): any[] {
    if (!receivers || !searchText) return receivers;
    searchText = searchText.toLowerCase();
    return receivers.filter(r => {
      const name = r.name ? r.name.toLowerCase() : '';
      const addr = r.receiverAddress ? r.receiverAddress.toLowerCase() : '';
      return name.includes(searchText) || addr.includes(searchText);
    });
  }
}
