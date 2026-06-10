import 'package:cinevault_flutter/main.dart';
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  testWidgets('CineVault opens from splash to movie home', (tester) async {
    await tester.pumpWidget(const CineVaultApp());

    expect(find.text('Explore Movies'), findsOneWidget);

    await tester.tap(find.text('Explore Movies'));
    await tester.pumpAndSettle();

    expect(find.text('Action Movies'), findsOneWidget);
    expect(find.text('John Wick 4'), findsOneWidget);
    expect(find.byIcon(Icons.search), findsOneWidget);
  });
}
