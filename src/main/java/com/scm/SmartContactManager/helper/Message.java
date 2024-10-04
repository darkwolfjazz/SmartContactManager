package com.scm.SmartContactManager.helper;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

private String content;
@Builder.Default
private MessageTypeEnum type=MessageTypeEnum.blue;


}
