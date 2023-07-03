// ** Import React

// ** Import MUI

// ** Import Components
import Template from "pages/Template";
import { BaseContainer, BaseItem, Title } from "components/views/ui";

const ListPage = () => {
  return (
    <Template name="発表資料一覧">
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"一覧"} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default ListPage;
