import { Controller, Get } from '@nestjs/common';
import { ApiOkResponse, ApiOperation, ApiProperty, ApiTags } from '@nestjs/swagger';

class HelloResponse {
  @ApiProperty({ example: 'Hello from Marketplace' })
  message!: string;
}

@ApiTags('Marketplace')
@Controller()
export class HelloController {
  @Get('hello')
  @ApiOperation({ summary: 'Say hello from Marketplace' })
  @ApiOkResponse({ description: 'Marketplace greeting', type: HelloResponse })
  hello(): HelloResponse {
    return { message: 'Hello from Marketplace' };
  }
}
