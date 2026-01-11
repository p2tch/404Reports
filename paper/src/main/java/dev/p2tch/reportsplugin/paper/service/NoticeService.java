package dev.p2tch.reportsplugin.paper.service;

import com.google.inject.Inject;
import dev.p2tch.reportsplugin.paper.object.MessageTemplate;
import dev.p2tch.reportsplugin.paper.object.text.ColoredText;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Map;

public class NoticeService {
    private final Logger logger;

    @Inject
    public NoticeService(final @NotNull Logger logger) {
        this.logger = logger;
    }

    public Builder builder() {
        return new Builder();
    }

    public class Builder {
        private ColoredText message;

        public Builder message(final @NotNull MessageTemplate message) {
            this.message = new ColoredText(message.getText());

            return this;
        }

        public Builder replace(final @NotNull String oldValue, final @NotNull String newValue) {
            if (validate()) return this;

            this.message = new ColoredText(
                    message.getText().replace(
                            oldValue, newValue
                    )
            );

            return this;
        }

        public Builder replaceAll(final @NotNull Map<String, String> replacements) {
            if (validate()) return this;

            String text = message.getText();

            for (final var entry : replacements.entrySet()) {
                text = text.replace(entry.getKey(), entry.getValue());
            }

            this.message = new ColoredText(text);
            return this;
        }


        public void send(final @NotNull Player player) {
            if (validate()) return;

            player.sendMessage(
                    message.build()
            );
        }

        public boolean validate() {
            if (message == null) {
                logger.error("Message cannot be null!");

                return true;
            }

            return false;
        }

        public Component getAsComponent() {
            return message.build();
        }
    }
}
