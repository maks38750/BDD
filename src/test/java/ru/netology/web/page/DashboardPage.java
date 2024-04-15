package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void Dashboard() {
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        val text = cards.find(Condition.attribute("data-test-id", cardInfo.getTestId())).text();
        return extractBalance(text);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer (String testId) {
        $("[data-test-id='" + testId + "'] [data-test-id='action-deposit']").click();
        return new TransferPage();
    }
}