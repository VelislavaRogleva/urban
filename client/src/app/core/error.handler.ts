import { ErrorHandler, Inject, Injector, Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";

@Injectable()
export class GlobalErrorHandler extends ErrorHandler {

  constructor(@Inject(Injector) private injector: Injector) {
    super();
  }

  private get toastrService(): ToastrService {
    return this.injector.get(ToastrService);
  }

  public handleError(error: any): void {
    this.toastrService.error(
      error.error['error'],
      "Error",
      {
        closeButton: true,
        timeOut: 5000,
        onActivateTick: true
      }
    );

    super.handleError(error);
  }
}

